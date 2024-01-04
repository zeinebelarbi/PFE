import { Component, OnInit, EventEmitter, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CantineService } from 'src/app/services/cantine.service';
import { DishService } from 'src/app/services/dish.service';
import { MenucategoryService } from 'src/app/services/menucategory.service';
import { GlobalConstants } from 'src/app/shared/global.constants';
@Component({
  selector: 'app-dish',
  templateUrl: './dish.component.html',
  styleUrls: ['./dish.component.scss']
})
export class DishComponent implements OnInit {
  onAddDish = new EventEmitter();
  onEditDish = new EventEmitter();
  dishForm: any = FormGroup;
  dialogAction: any = "Add";
  action: any = "Add";
  responseMessage: any;
  menucategorys: any = [];
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any, private formBuilder: FormBuilder,
    private dishService: DishService,
    public dialogRef: MatDialogRef<DishComponent>,
    private menucategoryService: MenucategoryService,
    private cantineService: CantineService,
  ) { }

  ngOnInit(): void {
    this.dishForm = this.formBuilder.group({
      dishname: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      MenuCategoryId: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    if (this.dialogData.action === "Edit") {
      this.dialogAction = "Edit";
      this.action = "Update";
      this.dishForm.patchValue(this.dialogData.data);
    }
    this.getMenuCategorys();
  }
  getMenuCategorys() {
    this.menucategoryService.getMenuCategorys().subscribe((response: any) => {
      this.menucategorys = response;
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage, GlobalConstants.error);
    })
  }
  handleSubmit() {
    if (this.dialogAction === "Edit") {
      this.edit();
    }
    else {
      this.add();
    }
  }
  add() {
    var formData = this.dishForm.value;
    var data = {
      iddish: null,
      dishname: formData.dishname,
      MenuCategoryId: formData.MenuCategoryId,
      price: formData.price,
      description: formData.description
    };
  
    if (this.dialogData && this.dialogData.data && this.dialogData.data.iddish) {
      data.iddish = this.dialogData.data.iddish;
    }
  
    this.dishService.add(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onAddDish.emit();
      this.responseMessage = response.message;
      this.cantineService.openCantine(this.responseMessage, "success");
    }, (error) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage, GlobalConstants.error);
    });
  }
  
  
  
  edit() {
    var formData = this.dishForm.value;
    var data = {
      iddish: this.dialogData.data.iddish,
      dishname: formData.dishname,
      MenuCategoryId: formData.MenuCategoryId,
      price: formData.price,
      description: formData.description
    }
    this.dishService.update(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onEditDish.emit();
      this.responseMessage = response.message;
      this.cantineService.openCantine(this.responseMessage, "success");
    }, (error) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage, GlobalConstants.error);
    });
  }
}
