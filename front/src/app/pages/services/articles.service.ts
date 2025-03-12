import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
  articleComment,
  articleResponse,
  newArticleModel,
} from '../models/articles.models';
@Injectable({
  providedIn: 'root',
})
export class ArticlesService {
  private articlesUrl = 'api';
  constructor(private httpClient: HttpClient) {}

  createArticle(article: newArticleModel): Observable<string> {
    return this.httpClient.post<string>(
      `${this.articlesUrl}/article/create`,
      article
    );
  }

  getAllArticles(): Observable<articleResponse[]> {
    return this.httpClient.get<articleResponse[]>(
      `${this.articlesUrl}/articles`
    );
  }
}
