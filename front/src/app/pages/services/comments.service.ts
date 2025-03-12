import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { articleComment } from '../models/articles.models';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  private commentsUrl = 'api';
  constructor(private httpClient: HttpClient) {}
  addComment(id: number, newComment: string) {
    return this.httpClient.post<string>(`${this.commentsUrl}/comments`, {
      articleId: id,
      content: newComment,
    });
  }
  getComments(id: number) {
    return this.httpClient.get<articleComment[]>(
      `${this.commentsUrl}/comments/${id}`
    );
  }
}
