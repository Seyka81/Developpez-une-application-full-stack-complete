export interface newArticleModel {
  themeId: number;
  title: string;
  content: string;
}

export interface articleResponse {
  id: number;
  author: string;
  theme: string;
  title: string;
  content: string;
  createdAt: string;
  updatedAt: string | null;
}

export interface articleComment {
  id: number;
  articleId: number;
  author: string;
  content: string;
  createdAt: string;
}
