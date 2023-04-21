import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';

import * as tarotServices from './services/tarot.services';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';


// const appRoutes: Routes = [
//   {path:'', component:HomeComponent},
//   {path: 'product/:terms', component:SearchComponent},
//   {path: '**', redirectTo: '/', pathMatch: 'full'}
// ]
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,

  ],
  providers: [tarotServices.TarotService],
  bootstrap: [AppComponent]
})
export class AppModule { }
