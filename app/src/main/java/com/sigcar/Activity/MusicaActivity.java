package com.sigcar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sigcar.Adapter.ArtistListAdapter;
import com.sigcar.Classes.Artist;
import com.sigcar.DAO.ConfiguracaoFirebase;
import com.sigcar.Helper.Base64Custom;
import com.sigcar.Helper.DateCustom;
import com.sigcar.R;

import java.util.ArrayList;
import java.util.List;

public class MusicaActivity extends AppCompatActivity {


    EditText editTextName;
    EditText editTextIdade;
    Button buttonAddArtist;
    Spinner spinnerGenres;
    ListView listViewArtists;
    EditText editTextData;


    List<Artist> artistList;

//    private DatabaseReference databaseArtists = ConfiguracaoFirebase.getFirebase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAuth();

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);



        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);
        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        editTextIdade = (EditText) findViewById( R.id.editTextIdade );
        editTextData =  (EditText) findViewById( R.id.editTextData );
        artistList = new ArrayList<>();

//        editData.setText( DateCustom.dataAtual() );
//        recuperarDespesaTotal();

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        databaseArtists = FirebaseDatabase.getInstance().getReference("OleoMotor").child( idUsuario );




        buttonAddArtist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addArtist();
            }
        });


        listViewArtists.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Artist artist = artistList.get(i);
            new AlertDialog.Builder(this)
                    .setTitle("Delete " + artist.getArtistName() + " "
                    )
                    .setMessage("Você deseja excluir o registro selecionado?")
                    .setPositiveButton("Delete", (dialogInterface, i1) -> {
                        databaseArtists.child(artist.getArtistId()).removeValue();

                        Toast.makeText(this, artist.getArtistName() + ""+" Excluido com Sucesso", Toast.LENGTH_LONG).show();


                    })
                    .setNegativeButton("Cancel", (dialogInterface, i12) -> {
                        dialogInterface.dismiss();
                    })
                    .create()
                    .show();
            return true;
        });


        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Artist artist = artistList.get(i);

                // vamos chamar a nova activity, para trabalhar com as musicas (tracks)
                Intent intent = new Intent(getApplicationContext(), AddTrackActivity.class);

                // guardando o nome e o id do artista na intent, para ser recuperada
                // pela nova activity
                intent.putExtra("ARTIST_ID", artist.getArtistId());
                intent.putExtra("ARTIST_NAME", artist.getArtistName());

                // enfim, chama a activity
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {

        super.onStart();

//    public void recuperarDespesaTotal(){

//        String emailUsuario = autenticacao.getCurrentUser().getEmail();
//        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
//        DatabaseReference usuarioRef = databaseArtists.child("OleoMotor").child( idUsuario );

        databaseArtists.addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                artistList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }

                ArtistListAdapter adapter = new ArtistListAdapter(MusicaActivity.this, artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }



    private void addArtist() {

        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();
        String idade = editTextIdade.getText().toString();
        String date = editTextData.getText().toString();

        if( !TextUtils.isEmpty(name) ) {

            String id = databaseArtists.push().getKey();
            Artist artist = new Artist(id, name, genre, idade,date);
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this, "Anotação adicionada com Sucesso", Toast.LENGTH_LONG).show();
        }
        else {

            Toast.makeText(this, "Você deve digitar um nome", Toast.LENGTH_LONG).show();
        }
    }
}