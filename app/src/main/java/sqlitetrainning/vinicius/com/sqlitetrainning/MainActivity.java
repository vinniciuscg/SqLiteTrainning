package sqlitetrainning.vinicius.com.sqlitetrainning;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Criação do banco
            SQLiteDatabase banco = openOrCreateDatabase("bancoApp", MODE_PRIVATE, null);

            banco.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3), profissao VARCHAR)");

            //Populando tabelas (comentar depois da primeira execução pra evitar inserir de novo)
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Marcos',30,'motorista')");
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Luan',15,'garçom')");
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Márcia',20,'engeheira')");
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Antônia',65,'secretária')");
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Tereza',50,'motorista')");
            banco.execSQL("INSERT INTO pessoas(nome, idade, profissao) VALUES ('Vitor',10,'estudante')");

            //Alterando informação de um único campo
            //banco.execSQL("UPDATE pessoas SET profissao='estudante' WHERE nome='Vitor'");

            //Deletando um registro
            //banco.execSQL("DELETE FROM pessoas WHERE nome='Vitor'");

            //Deletando tabela
            //banco.execSQL("DROP TABLE pessoas");

            //Realizando buscas
            Cursor cursor = banco.rawQuery("SELECT nome, idade, profissao FROM pessoas", null);
            // ou
            //Cursor cursor = banco.rawQuery("SELECT * FROM pessoas", null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            int indiceProfissao = cursor.getColumnIndex("profissao");

            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("RESULTADO: ", cursor.getString(indiceNome) +", "+ cursor.getString(indiceIdade) +", "+ cursor.getString(indiceProfissao));
                Toast.makeText(this, cursor.getString(indiceNome) +", "+ cursor.getString(indiceIdade) +", "+ cursor.getString(indiceProfissao), Toast.LENGTH_SHORT).show();
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
