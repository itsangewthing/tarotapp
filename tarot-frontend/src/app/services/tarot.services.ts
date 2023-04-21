import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Card } from "../models";




@Injectable({
    providedIn: 'root'
  })

  export class TarotService{
   
    constructor(private http: HttpClient){}
 

  // 1------------- SEARCH CARDS BY NAME
    public searchCardsByName(searchName: string): Promise<Card[]>{
                const params = new HttpParams().set("searchName", searchName)
        
        const headers = new HttpHeaders()
                              .set('content-type', 'application/json')
                              .set('Access-Control-Allow-Origin', '*')
    
        return lastValueFrom(this.http.get<Card[]>(`/cards/search`, {headers, params}))
            }


//  // 2----  GET PRODUCT BY SEARCHING ID
//     public getProductById(goods_id: string): Promise<Card[]> {
//         // const params = new HttpParams().set("goods_name", goods_name)
//         const headers = new HttpHeaders()
//         .set('content-type', 'application/json')
//         .set('Access-Control-Allow-Origin', '*')

//     return lastValueFrom(
//     this.http.get<Card[]>(`/api/product/${goods_id}}`, {headers}))
//     }


        }