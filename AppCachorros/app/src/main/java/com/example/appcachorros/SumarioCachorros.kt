package com.example.appcachorros

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SumarioCachorros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumario_cachorros)

        val layoutLista: LinearLayout = findViewById(R.id.layout_lista)
        val apiCachorros = ConexaoApiCachorros.criar()

        var indicados: Int = 0
        var naoIndicados: Int = 0

        apiCachorros.get().enqueue(object : Callback<List<Cachorro>> {
            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {
                response.body()?.forEach {
                    if(it.indicadoCriancas){
                        indicados++
                    }else{
                        naoIndicados++
                    }

                    val tvIndicados: TextView = findViewById(R.id.tv_qtd_indicado)
                    tvIndicados.text = indicados.toString()

                    val tvNaoIndicados: TextView = findViewById(R.id.tv_qtd_nao_indicado)
                    tvNaoIndicados.text = naoIndicados.toString()

                    val totalCachorros: TextView = findViewById(R.id.tv_total_qtd)
                    totalCachorros.text = (naoIndicados + indicados).toString()

                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "-------------\n " +
                            "Id: ${it.id} \n " +
                            "Raça: ${it.raca} \n " +
                            "Preço médio: ${it.precoMedio}  \n " +
                            "Indicado para crianças: ${it.indicadoCriancas}"

                    layoutLista.addView(tvCachorro)
                }
            }

            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Erro", "Erro Louco: " + t.message!!)
            }
        })
    }
}