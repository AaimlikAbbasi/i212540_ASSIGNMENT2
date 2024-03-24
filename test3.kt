import com.google.firebase.database.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    // Previous test cases...

    @Test
    fun testFetchMentors() {
        val mockDatabaseReference = mock(DatabaseReference::class.java)
        val mockDataSnapshot = mock(DataSnapshot::class.java)

        // Sample mentor data
        val mentor1 = Mentor("mentor123", "John Doe", "john@example.com")
        val mentor2 = Mentor("mentor456", "Jane Smith", "jane@example.com")
        val mentorList = listOf(mentor1, mentor2)

        // Simulate successful fetching of mentor data
        `when`(mockDatabaseReference.addValueEventListener(any()))
            .thenAnswer {
                val listener = it.arguments[0] as ValueEventListener
                listener.onDataChange(mockDataSnapshot.apply {
                    `when`(children).thenReturn(mentorList.map {
                        val mockChildSnapshot = mock(DataSnapshot::class.java)
                        `when`(mockChildSnapshot.getValue(Mentor::class.java)).thenReturn(it)
                        mockChildSnapshot
                    })
                })
            }

        // Call the function to fetch mentors
        val fetchedMentors = fetchMentorsFromDatabase(mockDatabaseReference)

        // Verify that the fetched mentors match the expected list
        assert(fetchedMentors == mentorList)
    }

    private fun fetchMentorsFromDatabase(databaseReference: DatabaseReference): List<Mentor> {
        val mentorList = mutableListOf<Mentor>()
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mentorList.clear()
                for (snapshot in dataSnapshot.children) {
                    val mentor = snapshot.getValue(Mentor::class.java)
                    mentor?.let { mentorList.add(it) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle cancellation if needed
            }
        })
        return mentorList
    }
}
