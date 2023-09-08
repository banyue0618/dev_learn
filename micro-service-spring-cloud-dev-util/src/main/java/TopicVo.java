import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/4/23 10:56
 * @description:
 */
public class TopicVo {

    private String content;

    private List<TopicVo> topicVos;

    private TopicVo parentTopicVo;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TopicVo> getTopicVos() {
        return topicVos;
    }

    public void setTopicVos(List<TopicVo> topicVos) {
        this.topicVos = topicVos;
    }

    public TopicVo getParentTopicVo() {
        return parentTopicVo;
    }

    public void setParentTopicVo(TopicVo parentTopicVo) {
        this.parentTopicVo = parentTopicVo;
    }
}
