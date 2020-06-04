package com.example.scrumboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.List;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.ExpandableListView;
import android.widget.ListView;

import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener ;
import android.widget.AdapterView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Backlogs.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Backlogs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Backlogs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    Button bt;
    EditText et;
    TextView tv;
    ListView lv;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Backlogs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Backlogs.
     */
    // TODO: Rename and change types and number of parameters
    public static Backlogs newInstance(String param1, String param2) {
        Backlogs fragment = new Backlogs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /**  expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });**/




    }


      // @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_backlogs, container, false);


        final ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.expandableListView);
        final SavedTabsListAdapter adapt = new SavedTabsListAdapter();
        elv.setAdapter(adapt);
        elv.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, int position, final long id) {
               // if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    final int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    final int childPosition = ExpandableListView.getPackedPositionChild(id);

                    // You now have everything that you would as if this was an OnChildClickListener()
                    // Add your logic here.
                    PopupMenu popup = new PopupMenu(view.getContext(), v);
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(view.getContext(),"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                           switch(item.getItemId()) {
                               case R.id.edit:
                                   LayoutInflater inflater = LayoutInflater.from(Backlogs.this.getContext());
                                   final View yourCustomView = inflater.inflate(R.layout.dialogue_box, null);

                                   final TextView etName = (EditText) yourCustomView.findViewById(R.id.txtSub);
                                   AlertDialog dialog = new AlertDialog.Builder(Backlogs.this.getContext())
                                           .setTitle("Enter task/user:")
                                           .setView(yourCustomView)
                                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int whichButton) {
                                                   if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                                                       adapt.children[groupPosition][childPosition] = etName.getText().toString();
                                                   } else {
                                                       adapt.groups[groupPosition] = etName.getText().toString();
                                                   }
                                               }
                                           })
                                           .setNegativeButton("Cancel", null).create();
                                   dialog.show();
                                   break;
                               case R.id.move:
                                   if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                                       Intent intent = new Intent(getActivity().getBaseContext(),
                                               MainActivity.class);

                                       String message = adapt.children[groupPosition][childPosition].toString();

                                       intent.putExtra("message", message);
                                       getActivity().startActivity(intent);
                                       Doing fragment2 = new Doing();
                                       Bundle args = new Bundle();
                                       args.putString("message", message);
                                       args.putInt("group",groupPosition);
                                       args.putInt("child",childPosition);
                                       fragment2.setArguments(args);
                                       getFragmentManager().beginTransaction().commit();
                                   }
                           }
                            return true;
                        }
                    });
                    popup.show();
                    // Return true as we are handling the event.
                    return true;
               // }

                //return false;
            }
        });
        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
       super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

   @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class SavedTabsListAdapter extends BaseExpandableListAdapter {

        public String[] groups = { "Employee1", "Employee2", "Employee3", "Employee4","Employee5" };

        public String[][] children = {
                { "Subtask1", "Subtask2", "Subtask3", "Subtask4" },
                { "Subtask1", "Subtask2", "Subtask3", "Subtask4" },
                { "Subtask1", "Subtask2", "Subtask3", "Subtask4" },
                { "Subtask1", "Subtask2", "Subtask3", "Subtask4"},
                { "Subtask1", "Subtask2", "Subtask3", "Subtask4" }
        };

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(Backlogs.this.getActivity());
          /**  EditText extView = new EditText(Backlogs.this.getActivity());
            extView.setFocusable(true);
            extView.setEnabled(true);
            extView.setCursorVisible(true);
            extView.setKeyListener(textView.getKeyListener());**/
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            textView.setText(getGroup(i).toString());
            textView.setTextSize(21);
            textView.setPadding(0, 15, 0, 15);
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(Backlogs.this.getActivity());
          /**  EditText extView = new EditText(Backlogs.this.getActivity());
            extView.setFocusable(true);
            extView.setEnabled(true);
            extView.setCursorVisible(true);
            extView.setKeyListener(textView.getKeyListener());**/
            textView.setText(getChild(i, i1).toString());
            textView.setTextSize(20);
            textView.setPadding(0, 15, 0, 15);
            return textView;
        }


        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }




}}
