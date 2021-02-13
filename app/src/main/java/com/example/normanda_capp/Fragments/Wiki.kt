package com.example.normanda_capp.Fragments

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshexample.AdaptorItemFile
import com.example.freshexample.Items
import com.example.normanda_capp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Wiki : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val list = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ///////////////////ADD THE FUCKING LIST HERE\\\\\\\\\\\\\\\\\\\\\\\\\
        //Swords
        list.add(Items(R.drawable.espada0,"Initial", "10","+5 Damage", "Wood Sword, gift from your master.", R.drawable.gold_bag))
        list.add(Items(R.drawable.espada1,"Radier", "20","+10 Damage", "French sword, weak as their army.", R.drawable.gold_bag))
        list.add(Items(R.drawable.espada3,"Bastard", "30","+15 Damage", "Old Templary sword, with the power of all the men who die with it.", R.drawable.gold_bag))
        list.add(Items(R.drawable.espada4,"Claymore", "40","+20 Damage", "D'Artagnan Sword, a legendary weapon from the most unique tales ever written", R.drawable.gold_bag))

        //Shields
        list.add(Items(R.drawable.escudo0,"Initial", "10","+5 Armor", "Wood Shield, gift from your master.", R.drawable.gold_bag))
        list.add(Items(R.drawable.escudo1,"Nombril", "20","+10 Armor", "Shield from an 'escutcheon' from the heraldry of England", R.drawable.gold_bag))
        list.add(Items(R.drawable.escudo2,"Escutcheon", "30","+15 Armor", "Viking Shield, with the bless of Odin", R.drawable.gold_bag))
        list.add(Items(R.drawable.escudo3,"Mortimer", "40","+20 Armor", "Templar Shield, used in the first crusade, becoming a sacred weapon, used to kill pagans", R.drawable.gold_bag))

        //Potions
        list.add(Items(R.drawable.tall_potion,"Tall", "5","+5 Life", "Small Potion, from the 'Stars'", R.drawable.gold_bag))
        list.add(Items(R.drawable.venti_potion,"Venti", "15","+15 Life", "Medium Potion, from the 'Stars'", R.drawable.gold_bag))
        list.add(Items(R.drawable.trenta_potion,"Trenta", "50","+60 Life", "Big Potion, from the 'Stars'", R.drawable.gold_bag))

        //Treasure Maps
        list.add(Items(R.drawable.pergaminho_do_tesouro, "Map N2", "0", "New Items", "The king of pirates, gold roger, gave his life to this treasure. Reach the final one!", R.drawable.gold_bag))
        list.add(Items(R.drawable.pergaminho_do_tesouro, "Map N3", "0", "New Items", "The king of pirates, Gold Roger, once said 'My wealth and treasures? It's all yours, i left it all there!'", R.drawable.gold_bag))

        ///////////////////ADD THE FUCKING LIST HERE\\\\\\\\\\\\\\\\\\\\\\\\\
        //data class Items(val Image:Int, val name:String, val cost:String, val stat:String, val lore:String) {

        view.findViewById<RecyclerView>(R.id.rec_wiki).adapter = AdaptorItemFile(list)
        view.findViewById<RecyclerView>(R.id.rec_wiki).layoutManager = LinearLayoutManager(this.context)


    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Wiki().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}