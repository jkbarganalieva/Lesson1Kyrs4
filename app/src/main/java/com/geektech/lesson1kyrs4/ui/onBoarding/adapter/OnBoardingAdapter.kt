package com.geektech.lesson1kyrs4.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.lesson1kyrs4.R
import com.geektech.lesson1kyrs4.databinding.ItemOnboardingBinding
import com.geektech.lesson1kyrs4.model.OnBoard
import com.geektech.lesson1kyrs4.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        OnBoard(
            "Минеральная вода",
            "Укрепит иммунитет",
            R.raw.baby
//            "https://kajmak.ru/upload/iblock/139/139eb9ab535989633dbcca48ad5964b2.jpg"
        ),
        OnBoard(
            "Тан",
            "Когда болит голова)",
            R.raw.maintenance
//            "https://irecommend.ru/sites/default/files/product-images/85567/CcCT1fqzrEoHhPKrOLWysg.jpg"
        ),
        OnBoard(
            "Шоро",
            "Богат витаминами и минералами",
            R.raw.sand_clock
//            "https://dastarkhan24.kz/upload/iblock/a1c/a1c81d923d3f6ec755690eb37c7b0985.jpg"
        ),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.image.setAnimation(onBoard.image!!)
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc

            //binding.image.loadImage(onBoard.image.toString())
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }
    }
}