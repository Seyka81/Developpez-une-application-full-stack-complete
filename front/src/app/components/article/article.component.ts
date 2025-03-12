import { Component, EventEmitter, Input, Output } from '@angular/core';
import { articleResponse } from 'src/app/pages/models/articles.models';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent {
  @Input() article!: articleResponse;
  @Output() articleClicked = new EventEmitter<articleResponse>();

  onArticleClick(article: articleResponse) {
    this.articleClicked.emit(article);
  }
}
