package com.xesapp.webhook.service.impl;

import com.xesapp.webhook.constant.MessageTypeConstant;
import com.xesapp.webhook.request.GitLabPushRequest;
import com.xesapp.webhook.service.DingPushService;
import com.xesapp.webhook.service.EventService;
import com.xesapp.webhook.util.JsonUtil;
import com.xesapp.webhook.vendor.ding.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service("pushEventService")
@Slf4j
public class PushEventServiceImpl implements EventService {

    @Autowired
    private DingPushService dingPushService;

    @Override
    public void handleEvent(String json) throws IOException {
        GitLabPushRequest gitLabPushRequest = covertJson(json);
        String text = "- event : " + gitLabPushRequest.getObjectKind() + " \n" +
                "- project : " + gitLabPushRequest.getProject().getName() + "\n" +
                "- author : " + gitLabPushRequest.getUserName() + "\n" +
                "- branch ：" + gitLabPushRequest.getRef() + "\n" +
                "- total_commits ：" + gitLabPushRequest.getTotalCommitsCount() + "\n";

        dingPushService.pushMarkDownMessage(new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown(gitLabPushRequest.getObjectKind(), text), true));
    }

    private GitLabPushRequest covertJson(String json) throws IOException {
        return JsonUtil.deserializeFromJson(json, GitLabPushRequest.class);
    }
}
