package com.dishdiscoverers.foodrecipe.dongguo.z_showList

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.dishdiscoverers.foodrecipe.dongguo.Recipe
import com.dishdiscoverers.foodrecipe.dongguo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeScreenModel(
    private val repository: RecipeRepository
) :
    StateScreenModel<RecipeScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val list: List<Recipe>) : State()
    }

    suspend fun getAllRecipe() {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value =
                State.Result(list = repository.getAllRecipe())
        }
    }

    suspend fun searchRecipe(title: String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value =
                State.Result(list = repository.searchRecipesByTitle(title))
        }
    }


}