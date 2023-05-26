import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialRoutes } from './material.routing';
import { MaterialModule } from '../shared/material-module';
import { ViewBillDishsComponent } from './dialog/view-bill-dishs/view-bill-dishs.component';
import { ConfirmationComponent } from './dialog/confirmation/confirmation.component';
import { ChangePasswordComponent } from './dialog/change-password/change-password.component';
import { ManageMenucategoryComponent } from './manage-menucategory/manage-menucategory.component';
import { MenucategoryComponent } from './dialog/menucategory/menucategory.component';
import { ManageDishComponent } from './manage-dish/manage-dish.component';
import { DishComponent } from './dialog/dish/dish.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MaterialRoutes),
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule
  ],
  providers: [],
  declarations: [
    ViewBillDishsComponent,
    ConfirmationComponent,
    ChangePasswordComponent,
    ManageMenucategoryComponent,
    MenucategoryComponent,
    ManageDishComponent,
    DishComponent,
    ManageOrderComponent,
    ViewBillComponent,
    ManageUserComponent
  ]
})
export class MaterialComponentsModule {}
