import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ThemesService } from '../services/themes.service';
import { themeResponse } from '../models/themes.models';
import { ArticlesService } from '../services/articles.service';
import { newArticleModel } from '../models/articles.models';

@Component({
  selector: 'app-newarticle',
  templateUrl: './newarticle.component.html',
  styleUrls: ['./newarticle.component.scss'],
})
export class NewarticleComponent implements OnInit {
  themes: themeResponse[] = [];

  article: newArticleModel = {
    themeId: 0,
    title: '',
    content: '',
  };
  constructor(
    private themesService: ThemesService,
    private articlesService: ArticlesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.themesService
      .getThemesSubscribed()
      .subscribe((themes: themeResponse[]) => {
        this.themes = themes;
      });
  }

  submitArticle() {
    this.articlesService.createArticle(this.article).subscribe((res) => {
      this.router.navigate(['/articles']);
      console.log(res);
    });
  }
}
