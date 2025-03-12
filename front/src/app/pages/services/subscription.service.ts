import { Injectable } from '@angular/core';
import { themeResponse } from '../models/themes.models';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  private subscriptionUrl = 'api/subscribe';

  constructor(private httpClient: HttpClient) {}

  subscribe(theme: themeResponse): Observable<String> {
    return this.httpClient.post<String>(this.subscriptionUrl, theme.id);
  }
  unsubscribe(theme: themeResponse) {
    return this.httpClient.delete(`${this.subscriptionUrl}/${theme.id}`);
  }
}
