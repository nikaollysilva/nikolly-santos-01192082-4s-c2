package com.example.appcachorros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroCachorro : AppCompatActivity() {

    lateinit var etRaca: EditText
    lateinit var etPrecoMedio: EditText
    lateinit var swIndicado: Switch
    lateinit var etMensagem: TextView
    lateinit var ivCachorro: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cachorro)

        etRaca = findViewById(R.id.et_raca)
        etPrecoMedio = findViewById(R.id.et_preco_medio)
        swIndicado = findViewById(R.id.sw_indicado)

        etMensagem = findViewById(R.id.tv_mensagem_sucesso)
        ivCachorro = findViewById(R.id.iv_imagem_cachorro)
    }

    fun cadastrarCachorro(view: View) {

        val apiCachorro = ConexaoApiCachorros.criar()

        val novoCachorro = Cachorro(
            null,
            etRaca.text.toString(),
            etPrecoMedio.text.toString().toDouble(),
            swIndicado.isChecked
        )

        apiCachorro.post(novoCachorro).enqueue(object : Callback<Cachorro> {
            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 201){

                    etMensagem.text = "Cão cadastrado com Sucesso"
                    ivCachorro.visibility = View.VISIBLE

                } else{
                    Toast.makeText(baseContext, "Erro ao cadastrar cachorros!", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }


}