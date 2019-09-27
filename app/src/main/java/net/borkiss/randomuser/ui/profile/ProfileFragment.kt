package net.borkiss.randomuser.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.*
import net.borkiss.randomuser.R
import net.borkiss.randomuser.data.model.User
import net.borkiss.randomuser.ext.formatDate


class ProfileFragment : Fragment() {

    companion object {

        private val ARG_USER = "user"

        fun newInstance(user: User): ProfileFragment {

            val args = Bundle()

            val fragment = ProfileFragment()
            args.putParcelable(ARG_USER, user)
            fragment.arguments = args

            return fragment
        }
    }

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments!!.getParcelable(ARG_USER)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        tvTitle.text = user.title
        tvName.text = context?.getString(R.string.fullName, user.firstName, user.lastName)
        tvStreet.text = user.street
        tvCity.text = user.city
        tvState.text = user.state
        tvPostcode.text = user.postcode
        tvEmail.text = user.email
        tvDateOfBirth.text = user.dateOfBirth.date.formatDate()
        tvPhone.text = user.phone
        tvCellPhone.text = user.cell
        tvNationality.text = user.nationality
    }
}
