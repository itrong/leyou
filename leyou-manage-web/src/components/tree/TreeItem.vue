<template>
  <div>
    <v-list-tile
      @click="toggle" class="level1 pt-0 pb-0 mt-0 mb-0" :class="{'selected':isSelected}">
      <v-list-tile-avatar>
        <v-icon v-if="model.isParent">{{open ? 'folder_open' : 'folder'}}</v-icon>
        <v-icon v-if="!model.isParent">insert_drive_file</v-icon>
      </v-list-tile-avatar>
      <v-list-tile-content>
        <v-list-tile-title v-show="!beginEdit">
          <span>{{model.name}}</span>
        </v-list-tile-title>
        <input v-show="beginEdit" @click.stop="" v-model="model.name" @blur="afterEdit" @keydown.enter="afterEdit"/>
      </v-list-tile-content>
      <v-list-tile-action v-if="isEdit">
        <v-btn icon @mouseover="c1='primary'" @mouseout="c1=''" :color="c1" @click.stop="addChild">
          <i class="el-icon-plus"/>
        </v-btn>
      </v-list-tile-action>
      <v-list-tile-action v-if="isEdit">
        <v-btn icon @mouseover="c2='success'" @mouseout="c2=''" :color="c2" @click.stop="editChild">
          <i class="el-icon-edit"/>
        </v-btn>
      </v-list-tile-action>
      <v-list-tile-action v-if="isEdit">
        <v-btn icon @mouseover="c3='error'" @mouseout="c3=''" :color="c3" @click.stop="deleteChild">
          <i class="el-icon-delete"/>
        </v-btn>
      </v-list-tile-action>
    </v-list-tile>

    <v-list v-if="isFolder" v-show="open" class="ml-4 pt-0 pb-0" dense transition="scale-transition">
      <tree-item
        class="item"
        v-for="(model, index) in model.children"
        :key="index"
        :model="model"
        :url="url"
        :isEdit="isEdit"
        :nodes="nodes"
        :parentState="open"
        @handleAdd="handleAdd"
        @handleDelete="handleDelete"
        @handleEdit="handleEdit"
        @handleGet="handleGet"
        @handleClick="handleClick"
      >
      </tree-item>
    </v-list>
  </div>
</template>

<script>
  import Vue from 'vue'

  export default {
    name: "tree-item",
    props: {
      model: Object,
      url: String,
      isEdit: {
        type: Boolean,
        default: false
      },
      nodes: Object,
      parentState: Boolean
    },
    created() {
    },
    data: function () {
      return {
        c1: '',
        c2: '',
        c3: '',
        isSelected: false,
        open: false,
        beginEdit: false
      }
    },
    watch: {
      parentState(val) {
        if (!val) {
          this.open = val;
        }
      }
    },
    computed: {
      isFolder: function () {
        return this.model.children &&
          this.model.children.length > 0
      }
    },
    methods: {
      toggle: function () {
        // 将其它被选中项取消选中
        this.nodes.selected.isSelected = false;
        // 当前项被选中
        this.isSelected = true;
        // 保存当前选中项
        this.nodes.selected = this
        // 客户自己的点击事件回调
        this.handleClick(this.model);
        // 判断是否为顶级节点，顶级节点需要记录和替换
        if (this.model.parentId == 0) {
          // 判断打开节点是否是自己
          if (this.nodes.opened && this != this.nodes.opened) {
            // 不是，则关闭原来的节点
            this.nodes.opened.open = false;
          }
          // 将自己记录为打开的节点
          this.nodes.opened = this;
        }
        // 切换开闭状态
        this.open = !this.open;
        // 如果已经是叶子节点,结束
        if (!this.model.isParent) {
          return;
        }
        // 如果自己是关闭的，移除子节点数据结束
        if (!this.open) {
          Vue.set(this.model, 'children', null);
          return;
        }
        //获取子节点数据
        this.handleGet(this.model.id);
      },
      addChild: function () {
        this.handleAdd(this.model);
        // 判断是否为顶级节点，顶级节点需要记录和替换
        if (this.model.parentId == 0) {
          // 判断打开节点是否是自己
          if (this.nodes.opened && this != this.nodes.opened) {
            // 不是，则关闭原来的节点
            this.nodes.opened.open = false;
          }
          // 将自己记录为打开的节点
          this.nodes.opened = this;
        }
        //判断是否为叶子节点
        if (!this.model.isParent) {
          this.model.isParent = true;
          Vue.set(this.model, 'children', this.model.children);
        }
        // 切换开闭状态
        this.open = true;
      },
      deleteChild: function () {
        this.$message.confirm('此操作将永久删除数据，是否继续?', '提示', {
          confirmButtonText: '确定删除',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(this.model);
        }).catch(() => {
          this.$message.info('已取消删除');
        })
      },
      editChild() {
        this.beginEdit = true;
      },
      afterEdit() {
        if (this.beginEdit) {
          this.beginEdit = false;
          this.handleEdit(this.getSimpleNode(this.model));
        }
      },
      handleAdd(node) {
        this.$emit("handleAdd", this.getSimpleNode(node));
      },
      handleDelete(id) {
        this.$emit("handleDelete", id);
      },
      handleEdit(node) {
        this.$emit("handleEdit", this.getSimpleNode(node))
      },
      handleGet(id) {
        Vue.set(this.model, 'children', this.model.children);
        this.$emit("handleGet", id)
      },
      handleClick(node) {
        this.$emit("handleClick", this.getSimpleNode(node));
      },
      getSimpleNode(node) {
        return {
          id: node.id,
          name: node.name,
          parentId: node.parentId,
          isParent: node.isParent,
          sort: node.sort
        }
      }
    }
  }
</script>

<style scoped>
  .level1 {
    height: 40px;
  }

  .selected {
    background-color: rgba(105, 184, 249, 0.75);
  }
</style>
