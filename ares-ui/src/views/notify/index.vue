<template>
  <div class="app-container">
    <el-timeline>
      <el-timeline-item
        v-for="msg in msgList"
        :timestamp="parseTime(msg.createTime, '{y}-{m}-{d}')"
        :key="msg.id"
        :icon="msg.noticeType == 1 ? 'el-icon-position' : 'el-icon-message'"
        size="large"
        :type="msg.noticeType == 1 ? 'primary' : 'warning'"
        placement="top"
      >
        <el-card>
          <div slot="header" class="clearfix">
            <span>{{msg.noticeTitle}}</span>
            <span style="float: right; padding: 3px 0">{{msg.creator}} - {{msg.createTime}}</span>
          </div>
          <p v-html="msg.noticeContent"></p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import { getNotices } from "@/api/notify/message";

export default {
  name: "Message",
  data() {
    return {
      msgList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      getNotices().then((response) => {
        this.msgList = response.data;
      });
    },
  },
};
</script>