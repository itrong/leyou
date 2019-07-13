<template>

  <v-app :dark="dark">
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant.sync="mini"
      app
    >
      <v-toolbar flat class="transparent">
        <v-list class="pa-0">
          <v-list-tile avatar>
            <v-list-tile-avatar>
              <img src="../assets/2.jpg">
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title>Lu-IT</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-btn
                icon
                @click.stop="mini = !mini"
              >
                <v-icon>chevron_left</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-toolbar>
      <v-list class="pt-0" dense>
        <v-list-group v-for="item in items"
                      :key="item.title"
                      :prepend-icon="item.action"
                      no-action
        >
          <!--一级菜单 -->
          <v-list-tile slot="activator">
            <v-list-tile-content>
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <!-- 二级菜单 -->
          <v-list-tile v-for="subItem in item.items"
                       :key="subItem.title"
                       :to="item.path + subItem.path">
            <v-list-tile-content>
              <v-list-tile-title>{{ subItem.title }}</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-icon>{{ subItem.action }}</v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>


    <v-toolbar
      app
    >
      <v-toolbar-side-icon @click.stop="drawer = !drawer"/>
      <v-flex xs4></v-flex>
      <v-toolbar-title v-text="title"/>
      <v-spacer/>

      <v-spacer></v-spacer>
      <!-- 调色板 -->
      <v-btn icon @click.stop="dark = !dark">
        <v-icon>invert_colors</v-icon>
      </v-btn>
    </v-toolbar>

    <!--右侧页面内容-->
    <v-content>
      <!--右侧页面内容导航-->
      <v-breadcrumbs>
        <v-icon slot="divider">chevron_right</v-icon>
        <v-breadcrumbs-item>{{item1}}</v-breadcrumbs-item>
        <v-breadcrumbs-item>{{item2}}</v-breadcrumbs-item>
      </v-breadcrumbs>
      <!--右侧页面内容导航结束-->
      <div>
        <!--子页面模块-->
        <router-view/>
      </div>
    </v-content>
    <!--右侧页面内容结束-->
  </v-app>
</template>
<script>
  import menus from "../menu";

  export default {
    data() {
      return {
        dark: false,
        drawer: true,
        mini: false,
        right: null,
        title: 'ITLU商城后台管理',
        menuMap: {},
      }
    },
    computed: {
      items() {
        return menus;
      },
      item1() {
        const arr = this.$route.path.split("/");
        return this.menuMap[arr[1]].name;
      },
      item2() {
        const arr = this.$route.path.split("/");
        return this.menuMap[arr[1]][arr[2]];
      }
    },
    name: 'App',
    watch: {},
    created() {
      menus.forEach(m => {
        const p1 = m.path.slice(1);
        this.menuMap[p1] = {name: m.title};
        m.items.forEach(i => {
          this.menuMap[p1][i.path.slice(1)] = i.title;
        })
      })
    }
  }
</script>

