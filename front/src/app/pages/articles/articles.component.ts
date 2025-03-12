import { Component, OnInit } from '@angular/core';
import { ArticlesService } from '../services/articles.service';
import { articleComment, articleResponse } from '../models/articles.models';
import { CommentsService } from '../services/comments.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent implements OnInit {
  constructor(
    private articlesService: ArticlesService,
    private commentsService: CommentsService
  ) {}
  articles: articleResponse[] = [];
  isViewArticle = false;
  articleSelected: articleResponse | null = null;
  ordre: string = 'asc';
  ngOnInit(): void {
    this.articlesService
      .getAllArticles()
      .subscribe((articles: articleResponse[]) => {
        this.articles = articles;
        this.filter();
      });
  }
  articleClick(article: articleResponse) {
    this.isViewArticle = true;
    this.articleSelected = article;
    this.commentsService
      .getComments(this.articleSelected!.id)
      .subscribe((comments: articleComment[]) => {
        this.comments.next(comments);
      });
  }

  comments: BehaviorSubject<articleComment[]> = new BehaviorSubject<
    articleComment[]
  >([]);

  newComment: string = '';

  addComment() {
    this.commentsService
      .addComment(this.articleSelected!.id, this.newComment)
      .subscribe(() => {
        this.commentsService
          .getComments(this.articleSelected!.id)
          .subscribe((comments: articleComment[]) => {
            this.comments.next(comments);
          });
        this.newComment = '';
      });
  }
  filter() {
    if (this.ordre === 'desc') {
      this.ordre = 'asc';
    } else {
      this.ordre = 'desc';
    }
    console.log(this.ordre);
    if (this.ordre === 'desc') {
      this.articles.sort((a, b) => {
        return a.createdAt < b.createdAt ? 1 : -1;
      });
    } else {
      this.articles.sort((a, b) => {
        return a.createdAt > b.createdAt ? 1 : -1;
      });
    }
  }
}
