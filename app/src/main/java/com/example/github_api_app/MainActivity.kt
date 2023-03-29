package com.example.github_api_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textviewUsuario: TextView
    private lateinit var btnBuscar: Button
    private lateinit var reposRecyclerView: RecyclerView
    private lateinit var reposAdapter: ReposAdapter

    private lateinit var txtBuscador: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textviewUsuario = findViewById(R.id.textviewUsuario)
        txtBuscador = findViewById(R.id.txtBuscador)
        btnBuscar = findViewById(R.id.btnBuscar)
        reposRecyclerView = findViewById(R.id.repos_recycler_view)


        reposAdapter = ReposAdapter()
        reposRecyclerView.adapter = reposAdapter
        reposRecyclerView.layoutManager = LinearLayoutManager(this)

        txtBuscador.addTextChangedListener( object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                bindData()
            }
        })

        btnBuscar.setOnClickListener {
            bindData()
        }

    }



    fun bindData() {
        val username = txtBuscador.text.toString()
        if (username.isNotEmpty()) {
            GithubApiClient.getRepos(username).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    Log.d(TAG, response.toString())
                    if (response.isSuccessful) {
                        val repos = response.body()
                        if (repos != null) {
                            reposAdapter.setData(repos)
                            textviewUsuario.setText("Usuario: ${username} \nRepositorios públicos: ${repos.size}")

                        } else {
                            textviewUsuario.setText("No se han encontrado repositorios")
                        }
                    }

                    if(response.errorBody()!=null){
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

                        textviewUsuario.setText("Error: \n${jsonObj.get("message").toString()}")
                        reposAdapter.setData(emptyList())
                    }

                    if (response.code().toInt() == 404) {
                        textviewUsuario.setText("No se ha encontrado el usuario")
                        reposAdapter.setData(emptyList())
                    }

                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()

                    textviewUsuario.setText("Error: ${t.message}")
                }


            })
        } else {
            textviewUsuario.setText("")
            reposAdapter.setData(emptyList())
        }
    }


}