import Vue from 'vue'
import Router from 'vue-router'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Router)

function route (path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import('../pages' + file)
  }
}

export default new Router({

  routes: [
    route("/login",'/Login',"Login"),
    {
      path: '/',
      component: () => import('../pages/Layout'),
      redirect:"/index/dashboard",
      children:[
        route("/index/dashboard","/Dashboard","Dashboard"),
        route("/item/category","/item/Category","itemCategory"),
        route("/item/brand","/item/Brand","itemBrand"),
        route("/item/list","/item/List","itemList"),
        route("/item/specification","/item/Specification","itemSpecification"),
        route("/user/statistics","/user/Statistics","userStatistics"),
        route("/user/list","/user/List","userList"),
        route("/trade/statistics","/trade/Statistics","tradeStatistics"),
        route("/trade/order","/trade//Order","tradeOrder"),
        route("/trade/logistics","/trade//Logistics","tradeLogistics"),
        route("/trade/promotion","/trade//Promotion","tradePromotion"),
        route("/authority/list","/authority/List","authorityList"),
        route("/authority/role","/authority/Role","authorityRole"),
        route("/authority/member","/authority/Member","authorityMember"),
      ]
    }
  ]
})
