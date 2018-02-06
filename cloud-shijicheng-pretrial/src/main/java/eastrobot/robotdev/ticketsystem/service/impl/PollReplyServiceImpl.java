package eastrobot.robotdev.ticketsystem.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.PollReplyDao;
import eastrobot.robotdev.ticketsystem.model.vo.DbReturnOption;
import eastrobot.robotdev.ticketsystem.model.vo.DbReturnTopic;
import eastrobot.robotdev.ticketsystem.model.vo.Option;
import eastrobot.robotdev.ticketsystem.model.vo.Record;
import eastrobot.robotdev.ticketsystem.service.PollReplyService;


/**
 * Project:
 * Author: HONGMIN GAO
 * Date: 27/9/17
 * Descripthion: ...
 */
@Service("pollReplyService")
public class PollReplyServiceImpl implements PollReplyService {

    @Autowired
    private PollReplyDao pollReplyDao;

    @Override
    public List<Record> findSurveyLists(Integer surveyId) throws Exception {

        DecimalFormat df = new DecimalFormat("0.00");
        List<DbReturnTopic> dbReturnTopicList = this.pollReplyDao.findDbReturnTopicByTopicId(surveyId);
        List<DbReturnOption> dbReturnOptions = this.pollReplyDao.findDbReturnOptionByTopicId(surveyId);

        List<Record> records = new ArrayList<Record>();
        //遍历题目
        for (DbReturnTopic topic : dbReturnTopicList) {
            List<Option> options = new ArrayList<Option>();
            Record _record = new Record();

            String type;
            _record.setId(topic.getId());
            if (topic.getTopicType() == 1 || topic.getTopicType() == 0) {
                type = "option";
            } else {
                type = "reply";
            }
            _record.setType(type);
            _record.setTitle(topic.getTitle());
            _record.setSum(topic.getTopicSum().intValue());

            //遍历内容
            for (DbReturnOption option : dbReturnOptions) {
                Option _option = new Option();
                //同种问题 挂靠题目
                if ((option.getId() == topic.getId())) {
                    _option.setId(option.getOptionId());
                    //回复类型 内容设置
                    if (option.getTopicType() == 2) {
                        _option.setContent(option.getContent());
                        options.add(_option);
                        //单选 多选内容设置 数目设置 百分比
                    } else if (option.getTopicType() < 2) {
                        _option.setContent(option.getOptionContent());
                        _option.setNum(option.getCountOption().intValue());
                        _option.setPercent(df.format((float) _option.getNum() * 100 / _record.getSum()) + "");
                        options.add(_option);
                    }
                }

            }
            _record.setOptions(options);
            records.add(_record);
        }

        //topic表里的实际记录
        List<DbReturnTopic> allTopicList = this.pollReplyDao.findDbReturnTopicAllByTopicId(surveyId);
        //option表里单选多选的实际记录
        List<DbReturnOption> dbOptions = this.pollReplyDao.findDbReturnOptionAllByTopicId(surveyId);

        List<Record> allRecords = new ArrayList<Record>();
        //重新组装topic
        for (DbReturnTopic topic : allTopicList) {
            List<Option> options = new ArrayList<Option>();
            Record _record = new Record();

            String type;
            _record.setId(topic.getId());
            if (topic.getTopicType() == 1 || topic.getTopicType() == 0) {
                type = "option";
            } else {
                type = "reply";
            }
            _record.setType(type);
            _record.setTitle(topic.getTitle());
            //初始化0
            _record.setSum(0);
            //从replay里topic里得到sum
            for (Record record : records) {
                if (record.getId() == topic.getId()) {
                    _record.setSum(record.getSum());
                    break;
                }
            }

            //先挂上option里的选项
            for (DbReturnOption dbOption : dbOptions) {
                if (dbOption.getId() == topic.getId()) {
                    Option _option = new Option();
                    _option.setId(dbOption.getOptionId());
                    _option.setContent(dbOption.getContent());

                    //初始为0
                    _option.setNum(0);
                    _option.setPercent("0");
                    //如果在replay有单选多选记录则挂上num 和 Percent
                    for (Record record : records) {
                        if (record.getId() == topic.getId() && record.getType().equals("option")) {
                            List<Option> replyOptions = record.getOptions();
                            //如果optionid 相等，则把replyoption赋值到option里.
                            for (Option replyOption : replyOptions) {
                                if (_option.getId() == replyOption.getId()) {
                                    _option.setNum(replyOption.getNum());
                                    _option.setPercent(replyOption.getPercent());
                                }
                            }
                        }
                    }

                    options.add(_option);
                }
            }

            //再挂上replay的回复选项option
            for (Record record : records) {
                if (record.getId() == topic.getId() && record.getType().equals("reply")) {
                    options = record.getOptions();
                    break;
                }
            }

            _record.setOptions(options);
            allRecords.add(_record);
        }
        return allRecords;
    }

    @Override
    //删除问卷ID下的所有回复
    public void deletePollReplyBySurveyId(Integer surveyId) {
        pollReplyDao.deletePollReplyBySurveyId(surveyId);
    }
}
