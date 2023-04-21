import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { TarotService } from 'src/app/services/tarot.services';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  form!: FormGroup
  param$!: Subscription

  constructor(private fb: FormBuilder, private router: Router, private ar: ActivatedRoute,
    private tarotSvc: TarotService) {}
  
  ngOnInit(): void {
    this.form = this.createForm()
  }

  searchName() {
    const searchName: string = this.form.value['searchName']
    console.log("searching.. ", searchName)

    // route to search page
    this.router.navigate([`/product/${searchName}`])

  }

  private createForm(): FormGroup {
    return this.fb.group({
      searchTerms: this.fb.control<string>('', [ Validators.required ])
    })
  }

}
