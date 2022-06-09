var app = new Vue({
	el: '#app',
	data() {
		return {
			items: {}
		}
	},
	mounted() {
		this.loadArticles();
	},
	methods: {
		loadArticles() {
			this.items = JSON.parse(localStorage.getItem('articlesInCard'));
			console.log(this.items);
		}
	}
});