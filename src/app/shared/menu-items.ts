import { Injectable } from "@angular/core";
export interface Menu{
    state:string;
    name:string;
    type:string;
    icon:string;
    role:string;
}
const MENUITEMS =[
    {state:'dashboard',name:'Dasboard',type:'link', icon:'dashboard',role:'admin'},
    {state:'menucategory',name:'Manage Menucategory ',type:'link', icon:'category',role:'admin'},
    {state:'dish',name:'Manage Dish ',type:'link', icon:'inventory_2',role:'admin'},
    {state:'order',name:'Manage Order ',type:'link', icon:'shopping_cart',role:'user'},
    {state:'user',name:'Manage User ',type:'link', icon:'people',role:'admin'},

]
@Injectable()
export class MenuItems{
    getMenuitem():Menu[]{
        return MENUITEMS;
    }
}