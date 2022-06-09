var app = new Vue({
	el: '#app',
	data() {
		return {
			articles: []
		}
	},
	mounted() {
		this.loadArticles();
	},
	methods: {
		loadArticles() {
			axios.get('/public/articles')
			.then(response => {
				this.articles = response.data.data;
			});
		},
		addArticleToCard(articleJson) {
			console.log(articleJson);
			let articlesMap = localStorage.getItem('articlesInCard');
  			let articleId = articleJson.id;
 			let articleCode = articleJson.code;
 			let articlePrix = articleJson.prix;
 			let articleInCard = 1;
   			let article = { id : articleId, code : articleCode, price : articlePrix, quantity: articleInCard};
   			let id = articleId;
   			
   			if (articlesMap == null){
				articlesMap = {};
				articlesMap[id] = article;
			} else {
				articlesMap = JSON.parse(articlesMap);

      			if(articlesMap[article.id] == undefined){   
			         	articlesMap[id] = article;
			      } else {
			        articlesMap[id].quantity +=1;
			      }    
			}
			//localStorage.clear();
			localStorage.setItem('articlesInCard',JSON.stringify(articlesMap));
			console.log(articlesMap) ;
		},
		goToCard() {
			window.location = '/public/panier.html';
		}
	}
});
