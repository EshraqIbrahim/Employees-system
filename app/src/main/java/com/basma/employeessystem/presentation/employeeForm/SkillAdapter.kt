package com.basma.employeessystem.presentation.employeeForm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basma.employeessystem.R
import com.basma.employeessystem.databinding.ItemSkillBinding
import com.basma.employeessystem.domain.model.Skill

class SkillAdapter(
    private val skills: ArrayList<Skill>,
    val onSkillRemoved: (Int) -> Unit
) : RecyclerView.Adapter<SkillAdapter.SkillsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SkillsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false))

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) = holder.bind(skills[position])

    override fun getItemCount() = skills.size

    inner class SkillsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSkillBinding.bind(itemView)

        fun bind(skill: Skill) {
            binding.skillName.text = skill.name
            binding.closeIcn.setOnClickListener { onSkillRemoved(skill.id!!) }
        }
    }
}