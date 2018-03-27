package com.pathak.rajat.pictlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity
{
    GridView gv;
    SearchView sc;

    String[] names = {"GEOGRAPHIC INFO. SYS ","ALGEBRA","ANTENNA & PROPAGATION","AUTOMATIC CONTROL ENGG","BIOINFORMATICS","BIOPHYSICS","CELLULAR TELEPHONY","CHEMISTRY","CIRCUITRY","CIVIL ENGG","COMMUNICATION ENGG","COMMUNICATION:TECHNICAL","COMPUTER FORENSICS","CONTROL SYS","CONTROL ENGG","DIGITAL EVIDENCE","DIGITAL IMAGE PROCESSING","DIGITAL INSTRUMENTATION","DIGITAL SIGNALS"," DISCRETE MATHS","ELEC. COMPONENT","ELECTRONICS","ELECTRONICS ENGG","ELECTRICAL ENGG","ENGINEERING CHEMISTRY","ENGG. CKT ANALYSIS","ENGG. DRAWING","ENGG MATHS","ENGG MECHANICS","ENGG PHYSICS","ENVIRONMENTAL STUDIES","FIBRE OPTICS","FLUID MECHANICS","FUZZY LOGIC","HEAT TRANSFER","INDUSTRIAL ENGG","MACHINE ENGG","MANAGEMENT","MATLAB","MECHATRONICS","MICROCONTROLLER","MICROWAVE ELECTRONICS","NETWORK & SYS","OPTICS","POLYMER SCI","PRINTED CKT","PROBABILITY","RADIO TELEPHONY","SATELLITE COMMUNICATION","SEMI CONDUCTOR","SOCIAL SCIENCES","TELEVISION","THERMODYNAMICS","VHDL","VLSI","WIRELESS SENSOR"};
    int[] images = {R.drawable.gis,R.drawable.algebra,R.drawable.antenna,R.drawable.automatic_control,R.drawable.bioinformatics,R.drawable.biopysics,R.drawable.cellulartelephony,R.drawable.chemistry,R.drawable.circuitry,R.drawable.civil_engg,R.drawable.comm_engg,R.drawable.comm_tech,R.drawable.comp_forensics,R.drawable.comtrolsys,R.drawable.control_engg,R.drawable.digitalevidence,R.drawable.digitalimage,R.drawable.digitalinstrumentation,R.drawable.digitalsignal,R.drawable.discretemaths,R.drawable.eleccomponent,R.drawable.electronics,R.drawable.electronicsengg,R.drawable.elelctricalengg,R.drawable.enggchemistry,R.drawable.enggcktanalysis,R.drawable.enggdrawing,R.drawable.enggmaths,R.drawable.enggmechanics,R.drawable.enggphysics,R.drawable.environmentalstudies,R.drawable.fibreoptics,R.drawable.fluidmech,R.drawable.fuzzylogic,R.drawable.heattransfer,R.drawable.industrial_engg,R.drawable.mach_engg,R.drawable.management,R.drawable.matlab,R.drawable.mechatronics,R.drawable.microcontroller,R.drawable.microwave_elec,R.drawable.networkandsys,R.drawable.optics,R.drawable.polymersci,R.drawable.printed_ckt,R.drawable.probability,R.drawable.radio_telephony,R.drawable.satellite_comm,R.drawable.semiconductor,R.drawable.social_sci,R.drawable.television,R.drawable.thermodynamics,R.drawable.vhdl,R.drawable.vlsi,R.drawable.wireless_sensor};

    ArrayList<Book> LIST = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        gv = (GridView)findViewById(R.id.gridview);
        sc  = (SearchView)findViewById(R.id.seachview);
        sc.setIconified(false);
        sc.setQueryHint("ENTER CATEGORY");

        final Adapter adapter = new Adapter(this,this.getBooks());
        gv.setAdapter(adapter);

        LIST = CategoryActivity.this.getBooks();

        sc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });



        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sc.setQuery(adapter.category.get(i).getName(),false);
                String cat =adapter.category.get(i).getName();
                cat = cat.replaceAll(" ","");
                Intent intent = new Intent(CategoryActivity.this,Category_Books.class);
                intent.putExtra("category",cat);
                startActivity(intent);

            }
        });
    }


    private ArrayList<Book> getBooks()
    {

        ArrayList<Book> books = new ArrayList<Book>();
        Book b;

        for(int i=0;i<names.length;i++)
        {
            b = new Book(names[i],images[i]);
            books.add(b);
        }

        return  books;
    }
}
