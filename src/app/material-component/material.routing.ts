import { Routes } from '@angular/router';
import { ManageMenucategoryComponent } from './manage-menucategory/manage-menucategory.component';
import { RouteGuardService } from '../services/route-guard.service';
import { ManageDishComponent } from './manage-dish/manage-dish.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';


export const MaterialRoutes: Routes = [
    {
        path :'menucategory',
component:ManageMenucategoryComponent,
canActivate:[RouteGuardService],
data: {
    expectedRole:['admin']
}
    },
    {
        path :'dish',
component:ManageDishComponent,
canActivate:[RouteGuardService],
data: {
    expectedRole:['admin']
}
    },
    {
        path :'order',
component:ManageOrderComponent,
canActivate:[RouteGuardService],
data: {
    expectedRole:['user']
}
    },
    {
        path :'bill',
component:ViewBillComponent,
canActivate:[RouteGuardService],
data: {
    expectedRole:['user']
}
    },
    {
        path :'user',
component:ManageUserComponent,
canActivate:[RouteGuardService],
data: {
    expectedRole:['admin']
}
    },


];
