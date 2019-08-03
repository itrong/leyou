<template>
  <v-list class="pt-0 pb-0" dense>
    <TreeItem
      class="item" :model="model" v-for="(model, index) in db" :key="index"
      :url="url"
      :isEdit="isEdit"
      :nodes="nodes"
      @handleAdd="handleAdd"
      @handleDelete="handleDelete"
      @handleEdit="handleEdit"
      @handleGet="handleGet"
      @handleClick="handleClick"
    />
  </v-list>
</template>

<script>
  import TreeItem from './TreeItem';

  export default {
    name: "vTree",
    props: {
      url: String,
      isEdit: {
        type: Boolean,
        default: false
      },
      treeData: {
        type: Array
      }
    },
    data() {
      return {
        db: [],
        nodes: {
          opened: null,
          selected: {isSelected: false}
        }
      }
    },
    components: {
      TreeItem
    },
    created() {
      if (this.treeData && this.treeData.length > 0) {
        this.db = this.treeData;
        return;
      }
      this.getData();
    },
    methods: {
      getData() {
        this.$http.get(this.url + 'list', {params: {pid: 0}}).then(resp => {
          this.db = resp.data;
        })
      },
      handleAdd(node) {
        this.$emit("handleAdd", node);
      },
      handleDelete(node) {
        this.$emit("handleDelete", node);
      },
      handleEdit(node) {
        this.$emit("handleEdit", node)
      },
      handleGet(id) {
        this.$emit("handleGet", id)
      },
      handleClick(node) {
        this.$emit("handleClick", node)
      },
      //设置子节点
      setChildren(id, children, arr) {
        let src = arr || this.db;
        for (let i in src) {
          let d = src[i]
          if (d.id == id) {
            d.children = children;
            return;
          }
          if (d.children && d.children.length > 0) {
            this.setChildren(id, children, d.children)
          }
        }
      }
    },
    watch: {}
  }
</script>

<style scoped>
  .item {
    cursor: pointer;
  }
</style>
