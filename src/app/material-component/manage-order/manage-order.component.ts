import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BillService } from 'src/app/services/bill.service';
import { CantineService } from 'src/app/services/cantine.service';
import { DishService } from 'src/app/services/dish.service';
import { MenucategoryService } from 'src/app/services/menucategory.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from 'src/app/shared/global.constants';
import { saveAs } from 'file-saver';
import { ClassGetter } from '@angular/compiler/src/output/output_ast';
@Component({
  selector: 'app-manage-order',
  templateUrl: './manage-order.component.html',
  styleUrls: ['./manage-order.component.scss']
})
export class ManageOrderComponent implements OnInit {
  displayedColumns: string[] = ['name', 'menucategoryname', 'description', 'price', 'quantity', 'edit'];
  dataSource: any = [];
  manageOrderForm: any = FormGroup;
  menucategorys: any = [];
  dishs: any = [];
  price: any;
  totalAmount: number = 0;
  responseMessage: any;
  constructor(private formBuilder: FormBuilder,
    private dishService: DishService,
    private menucategoryService: MenucategoryService,
    private cantineservice: CantineService,
    private billService: BillService,
    private ngxService: NgxUiLoaderService) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.getMenuCategorys();
    this.manageOrderForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      paymentMethod: [null, [Validators.required]],
      dishname: [null, [Validators.required]],
      menucategoryname: [null, [Validators.required]],
      quantity: [null, [Validators.required]],
      price: [null, [Validators.required]],
      total: [0, [Validators.required]]
    });
  }
  getMenuCategorys() {
    this.menucategoryService.getFilteredMenuCategorys().subscribe((response: any) => {
      this.ngxService.stop();
      this.menucategorys = response;
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineservice.openCantine(this.responseMessage, GlobalConstants.error);
    })
  }
  getDishsByMenucategory(value: any) {
    console.log(value)
    this.dishService.getDishsByMenucategory(value.idMenuCategory).subscribe((response: any) => {
      this.dishs = response;
      this.manageOrderForm.controls['price'].setValue('');
      this.manageOrderForm.controls['quantity'].setValue('');
      this.manageOrderForm.controls['total'].setValue(0);
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineservice.openCantine(this.responseMessage, GlobalConstants.error);
    })

  }

  getDishDetails(value: any) {
    console.log("===============================")
    this.dishService.getById(value.iddish).subscribe((response: any) => {
      console.log(this.dishs)
      this.price = response.price;
      console.log("=====>"+this.price)
      
      this.manageOrderForm.controls['price'].setValue(response.price);
      this.manageOrderForm.controls['quantity'].setValue(1);
      this.manageOrderForm.controls['total'].setValue(this.price * 1);
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineservice.openCantine(this.responseMessage, GlobalConstants.error);
      console.log(this.dishs)
    })
  }
  setQuantity(value: any) {
    var temp = this.manageOrderForm.controls['quantity'].value;
    if (temp > 0) {
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['quantity'].value * this.manageOrderForm.controls['price'].value);
    }
    else if (temp != '') {
      this.manageOrderForm.controls['quantity'].setValue('1');
      this.manageOrderForm.controls['total'].setValue(this.manageOrderForm.controls['quantity'].value *
        this.manageOrderForm.controls['price'].value);
    }
  }
  validateDishAdd() {
    if (this.manageOrderForm.controls['total'].value === 0 || this.manageOrderForm.controls['total'].value === null ||
      this.manageOrderForm.controls['quantity'].value <= 0) {
      return true;
    }
    else
      return false;

  }
  validateSubmit() {
    if (this.totalAmount === 0 || this.manageOrderForm.controls['name'].value === null ||
      this.manageOrderForm.controls['paymentMethod'].value === null) {
      return true;
    }
    else
      return false;
  }
  add() {
  
    this.cantineservice.openCantine(GlobalConstants.dishAdded, "success");
    var formData = this.manageOrderForm.value;
    console.log(formData)
   // this.dataSource.push({ iddish: formData.dishname.iddish, name: formData.dishname.name, menucategory: formData.menucategory.name, quantity: formData.quantity, price: formData.dishname.price, total: formData.total });
    this.dataSource.push({dishname:formData.dishname,quantity: formData.quantity })
  
    console.log(this.dataSource)
    var dishName = this.dataSource.find((e: { iddish: number }) => e.iddish === formData.dishname.iddish);
    this.totalAmount = this.totalAmount + formData.total;
    this.dataSource = [...this.dataSource];
    
    /*if (dishName === undefined) {
      this.totalAmount = this.totalAmount + formData.total;
      this.dataSource = [...this.dataSource];
      this.cantineservice.openCantine(GlobalConstants.dishAdded, "success");
    }
    else {
      this.cantineservice.openCantine(GlobalConstants.dishExistError, GlobalConstants.error);
    }*/
  }
  handleDeleteAction(value: any, element: any) {
    this.totalAmount = this.totalAmount - element.total;
    this.dataSource.splice(value, 1);
    this.dataSource = [...this.dataSource];
  }
  submitAction() {
    var formData = this.manageOrderForm.value;
    var data = {
     
      name: formData.name,
      paymentMethod: formData.paymentMethod,
      totalAmount: this.totalAmount.toString(),
      dishDetails: this.dataSource,
     
    }
    console.log(data)

    this.ngxService.start();
    this.billService.generateReport(data).subscribe((response: any) => {
      this.downloadFile(response?.uuid);
      this.manageOrderForm.reset();
      this.dataSource = [];
      this.totalAmount = 0;
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineservice.openCantine(this.responseMessage, GlobalConstants.error);
    })
  }
  downloadFile(fileName: string) { 
    var data={
      uuid:fileName
    }
    this.billService.getPdf(data).subscribe((response:any)=>{
     saveAs(response,fileName + '.pdf');
     this.ngxService.stop();
    })
  }

}

