package com.example.firsttest.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.firsttest.adapter.ArtRecyclerAdapter
import com.example.firsttest.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val artRecyclerAdapter: ArtRecyclerAdapter,

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtFragment::class.java.name-> ArtFragment(artRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            else-> super.instantiate(classLoader, className)
        }


        return super.instantiate(classLoader, className)
    }

}