package io.vasilenko.myrecipes.presentation.home

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import io.vasilenko.myrecipes.presentation.common.BaseDiffUtilItemCallback
import io.vasilenko.myrecipes.presentation.common.ListItem

class RecipesAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(HomeDelegates.bigRecipesDelegate())
            .addDelegate(HomeDelegates.mediumRecipesDelegate())
    }
}