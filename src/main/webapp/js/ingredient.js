var app = new Vue ({
	el: '#app',
	data(){
		return {
			ingredients: [],
			ingredient: {},
			displayIngredientForm: false
		}
	},
	mounted() {
		this.loadIngredients();
	},
	methods: {
		loadIngredients() {
			axios.get('/admin/ingredients')
			.then(response => {
				this.ingredients = response.data.data;
			});
		},
		sendIngredient() {
			axios.post('/admin/ingredient', this.ingredient)
			.then(response => {
				if (response.data.success) {
					this.loadIngredients();
					this.displayIngredientForm = false;
					this.ingredient = {};
				}
			});
		},
		deleteIngredient(ingredient) {
			axios.delete('/admin/ingredient?id=' + ingredient.id)
			.then(response => {
				if (response.data.success) {
					this.loadIngredients();
					this.ingredient = {};
				}
			});
		}
	}
});