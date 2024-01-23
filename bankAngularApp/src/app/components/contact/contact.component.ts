import { Component, OnInit } from '@angular/core';
import { Contact } from "src/app/model/contact.model";
import { NgForm } from '@angular/forms';
import { DashboardService } from 'src/app/services/dashboard/dashboard.service';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  model = new Contact();

  constructor(private dashboardService: DashboardService) {

  }

  // eslint-disable-next-line @angular-eslint/no-empty-lifecycle-method
  ngOnInit() {}

  saveMessage(contactForm: NgForm) {
    this.dashboardService.saveMessage(this.model).subscribe(
      responseData => {
        this.model = <Contact> responseData.body;
        contactForm.resetForm();
      });

  }

}
