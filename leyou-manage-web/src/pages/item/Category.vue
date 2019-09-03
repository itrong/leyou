<template>
  <v-card>
    <v-flex xs12 sm12>
      <v-tree ref="child"
              :url="url"
              :treeData="treeData"
              :isEdit="isEdit"
              @handleAdd="handleAdd"
              @handleDelete="handleDelete"
              @handleEdit="handleEdit"
              @handleGet="handleGet"
              @handleClick="handleClick"
      />
    </v-flex>
  </v-card>
</template>

<script>

  // import {treeData} from '../../mockDB'

  export default {
    name: "category",
    data() {
      return {
        url: '/item/category/',
        treeData: null,
        isEdit: true
      }
    },
    methods: {
      handleAdd(node) {
        console.log(node)
        //新增信息
        let child = {
          id: null,
          name: '新增节点',
          parentId: node.id,
          isParent: false,
          sort: node.children ? node.children.length + 1 : 1
        }
        this.$http({
          method: 'post',
          url: this.url,
          data: this.$qs.stringify(child),
        }).then(() => {
          this.handleGet(node.id);
        }).catch();
      },
      handleDelete(node) {
        //删除
        this.$http.delete(this.url, {params: {id: node.id}}).then(() => {
          //刷新父节点的子节点
          this.handleGet(node.parentId);
        }).catch();
      },
      handleEdit(node) {
        //编辑
        this.$http({
          method: 'put',
          url: this.url,
          data: this.$qs.stringify(node),
        }).then(() => {
          this.handleGet(node.parentId);
        }).catch();

        console.log(node)
      },
      handleGet(id) {
        console.log(id);
        //获取子节点
        this.$http.get(this.url + 'list', {params: {pid: id}})
          .then(resp => {
            this.$refs.child.setChildren(id, resp.data);
          }).catch(e => {
          console.log(e);
        });
      },
      handleClick(node) {
        // console.log(node)
      },
    }
  };
</script>

<style scoped>

</style>
