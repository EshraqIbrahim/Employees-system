package com.basma.employeessystem.presentation.employeeForm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.basma.employeessystem.domain.model.Skill
import java.util.*


class SkillDropDownAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    val allSkills: List<Skill>
) :
    ArrayAdapter<Skill>(context, layoutResource, allSkills), Filterable {

    private var skills: List<Skill> = allSkills

    override fun getCount() = skills.size

    override fun getItem(p0: Int) = skills[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
        createViewFromResource(position, convertView, parent)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?) =
        createViewFromResource(position, convertView, parent)

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = skills[position].name
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                skills = filterResults.values as List<Skill>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.lowercase(Locale.ROOT)

                val filterResults = FilterResults()
                if (queryString == null || queryString.isEmpty()) {
                    filterResults.values = allSkills
                } else {
                    filterResults.values = allSkills.filter {
                        it.name.lowercase(Locale.ROOT).startsWith(queryString)
                    }
                }
                return filterResults
            }
        }
    }
}
