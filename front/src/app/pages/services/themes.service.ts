import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { themeResponse } from '../models/themes.models';

@Injectable({
  providedIn: 'root',
})
export class ThemesService {
  private themesUrl = 'api/themes';
  constructor(private httpClient: HttpClient) {}

  public getThemes(): Observable<themeResponse[]> {
    return this.httpClient.get<themeResponse[]>(this.themesUrl);
  }

  public getThemesSubscribed(): Observable<themeResponse[]> {
    return this.httpClient.get<themeResponse[]>(this.themesUrl + '/subscribed');
  }
}
