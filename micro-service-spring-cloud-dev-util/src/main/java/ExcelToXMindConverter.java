import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xmind.core.Core;
import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/4/23 9:21
 * @description:
 */
public class ExcelToXMindConverter {

    private static final String excelFilePath = "D:\\信天翁\\概要设计\\海关概要设计\\系统整体概要设计功能V1.5.xlsx";

    private static final String xmindOutputPath = "D:/des/";

    public static void main(String[] args) {
        convertExcelToXMind();
    }

    public static void convertExcelToXMind() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            int sheets = 1;
            for(int i = 0 ; i < sheets ; i++){
                //获取Excel文件的第一个Sheet
                Sheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next(); //跳过表头
                rowIterator.next(); //跳过表头

                int startRowNum = 2;

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    //获取该行的第一个单元格
                    Cell firstCell = row.getCell(0);
                    if(firstCell.getStringCellValue() == ""){
                        continue;
                    }
                    String topicName = firstCell.getStringCellValue();
                    String xmindFileName = topicName + ".xmind";

                    //创建一个新的XMind文件
                    IWorkbook xmindWorkbook = Core.getWorkbookBuilder().createWorkbook();

                    int rowNum = getMergeRowNum(firstCell, sheet);

                    TopicVo rootTopicVo = new TopicVo();
                    rootTopicVo.setContent(topicName);
                    TopicVo topic = getTargetRowCellValue(1, startRowNum, rowNum, sheet, rootTopicVo);

                    startRowNum += rowNum;

                    //将解析的单元格内容创建为XMind文件的主题
                    ISheet xmindSheet = xmindWorkbook.getPrimarySheet();
                    ITopic rootTopic = xmindSheet.getRootTopic();
                    rootTopic.setTitleText(topicName);

                    List<ITopic> chapterTopics = buildITopic(topic.getTopicVos(), xmindWorkbook, sheet);

                    //把所有一级节点都加到根节点上
                    chapterTopics.forEach(it -> rootTopic.add(it, ITopic.ATTACHED));

                    //将XMind文件保存到文件系统中
                    String xmindFilePath = getXmindOutputPath(sheet.getSheetName(), topicName, xmindFileName);
                    File xmindFile = new File(xmindFilePath);
                    xmindFile.getParentFile().mkdirs();
                    xmindFile.createNewFile();

                    FileOutputStream outputStream = new FileOutputStream(xmindFilePath);

                    //将空的XMind文件压缩并写入到输出流中
                    xmindWorkbook.save(outputStream);

                    outputStream.close();
                    System.out.println("生成文档:" + xmindFilePath);
                }
            }
            workbook.close();
            excelFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("IOException: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Exception: " + ex.getMessage());
        }
    }

    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @Description: 获取当前cell合并的行数
     */
    public static int getMergeRowNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell)) {
                //获取合并的行数
                mergeSize = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                break;
            }
        }
        return mergeSize;
    }


    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @Description: 获取合并的列数
     */
    public static int getMergeColumnNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell)) {
                //获取合并的列数
                mergeSize = cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn() + 1;
                break;
            }
        }
        return mergeSize;
    }

    /**
     *
     * @param cellNum 列
     * @param startRowNum 从 startRowNum 开始读取
     * @param rowNum 要读取的行数
     * @param sheet
     * @return 指定列的所有行
     */
    public static TopicVo getTargetRowCellValue(int cellNum,int startRowNum, int rowNum, Sheet sheet, TopicVo rootTopic){
        List<TopicVo> topicVos = new ArrayList<>();
        for (int i = startRowNum; i < rowNum + startRowNum; i++) {
            Row row = sheet.getRow(i);
            //读取该行指定列
            Cell cell = row.getCell(cellNum);
            // 为null的情况 是由于某些行属于合并行，但是他并没有子集，属于excel内容不规范导致的。此时进行下一次循环。（不过break更好）
            if(cell == null){
                continue;
            }
            String value = cell.getStringCellValue();
            if(value != ""){
                TopicVo topicVo = new TopicVo();
                topicVo.setParentTopicVo(rootTopic);
                topicVo.setContent(value);
                topicVos.add(topicVo);
                rootTopic.setTopicVos(topicVos);
                // 有内容就继续往下找
                int rows = getMergeRowNum(cell, sheet);
                if(rows > 0){
                    getTargetRowCellValue(cellNum +1, row.getRowNum(), rows, sheet, topicVo);
                }
            }
        }
        return rootTopic;
    }

    public static List<ITopic> buildITopic(List<TopicVo> topicVos, IWorkbook xmindWorkbook, Sheet sheet) throws Exception{
        List<ITopic> chapterTopics = new ArrayList<>();
        if(topicVos != null && topicVos.size() > 0){
            for (TopicVo topicVo : topicVos) {
                // 如果包含子系统，那先生成子系统文件，然后再构造整个topic
                if(topicVo.getContent().contains("服务子系统") || topicVo.getContent().endsWith("公共服务平台") || topicVo.getContent().endsWith("管理子系统") || topicVo.getContent().endsWith("辅助中心")){
                    IWorkbook workbook = Core.getWorkbookBuilder().createWorkbook();
                    ISheet xmindSheet = workbook.getPrimarySheet();
                    ITopic rootTopic = xmindSheet.getRootTopic();
                    rootTopic.setTitleText(topicVo.getContent());
                    if(topicVo.getTopicVos() != null && topicVo.getTopicVos().size() > 0){
                        List<ITopic> iTopics = buildITopic(topicVo.getTopicVos(), workbook, sheet);
                        iTopics.forEach(e->rootTopic.add(e, ITopic.ATTACHED));
                    }

                    //将XMind文件保存到文件系统中
                    String xmindFilePath = getXmindOutputPath(sheet.getSheetName(), topicVo.getParentTopicVo().getContent() + File.separator + topicVo.getContent(), topicVo.getContent()+ ".xmind");
                    File xmindFile = new File(xmindFilePath);
                    xmindFile.getParentFile().mkdirs();
                    xmindFile.createNewFile();

                    FileOutputStream outputStream = new FileOutputStream(xmindFilePath);

                    //将空的XMind文件压缩并写入到输出流中
                    workbook.save(outputStream);

                    outputStream.close();
                    System.out.println("生成文档:" + xmindFilePath);
                }
                ITopic newTopic = xmindWorkbook.createTopic();
                newTopic.setTitleText(topicVo.getContent());
                chapterTopics.add(newTopic);
                if(topicVo.getTopicVos() != null && topicVo.getTopicVos().size() > 0){
                    List<ITopic> iTopics = buildITopic(topicVo.getTopicVos(), xmindWorkbook, sheet);
                    iTopics.forEach(e->newTopic.add(e, ITopic.ATTACHED));
                }

            }
        }
        return chapterTopics;
    }

    public static String getXmindOutputPath(String sheetName, String topicName, String fileName){
        return xmindOutputPath + File.separator + sheetName + File.separator + topicName + File.separator + fileName;
    }

}
