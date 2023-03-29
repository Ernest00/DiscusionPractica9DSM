package com.example.github_api_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    private var repos = emptyList<Repository>()

    fun setData(newRepos: List<Repository>) {
        repos = newRepos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repos_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewNombre: TextView = itemView.findViewById(R.id.viewNombre)
        private val viewDescripcion: TextView = itemView.findViewById(R.id.viewDescripcion)
        private val viewStars: TextView = itemView.findViewById(R.id.viewStars)
        private val viewForks: TextView = itemView.findViewById(R.id.viewForks)

        fun bind(repo: Repository) {
            viewNombre.text = repo.name
            viewDescripcion.text = repo.description
            viewStars.text = "Stars: ${repo.stargazers_count}"
            viewForks.text = "Forks: ${repo.forks_count}"
        }

    }

}