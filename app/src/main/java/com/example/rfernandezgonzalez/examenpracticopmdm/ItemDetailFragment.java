package com.example.rfernandezgonzalez.examenpracticopmdm;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rfernandezgonzalez.examenpracticopmdm.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);

            //Con esta declaracion se llama al boton borrar
            Button butBorrar = (Button) rootView.findViewById(R.id.buttonBorrar);

            //Se activa el metodo onClick sobre el boton
            butBorrar.setOnClickListener(new View.OnClickListener() {

                //Metodo que se utilizara para el intent para volver a la activity principal
                @Override
                public void onClick(View v) {

                    //Con esto hago que la app vea si esta en landscape o portrait y que metodo usar
                    ItemListFragment fragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);

                    if (fragment == null || !fragment.isInLayout()) {

                        Intent intresult = new Intent();
                        getActivity().setResult(Activity.RESULT_OK, intresult);
                        getActivity().finish();

                    } else {
                        delete();
                    }

                }


            });
        }
        return rootView;
    }

    //Metodo para borrar los datos que aparecen
    private void delete() {
        TextView view = (TextView) getView().findViewById(R.id.item_detail);
        view.setText("");
    }
}
