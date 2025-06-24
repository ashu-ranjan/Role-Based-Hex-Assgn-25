import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router-dom"

function UserList() {

    const [users, setUsers] = useState([])
    const [msg, setMsg] = useState("")

    // Set the token in localStorage for authorization
    let token = '30d2ff8432e9f0ab3225aab0c80373de4ad5d1e7167a702d254b19e03091cb5e'
    localStorage.setItem("token", token)
    // console.log("Token set in localStorage: ", localStorage.getItem("token"))

    // This component will display a list of users
    useEffect(() => {
        const getUsers = async() => { // Fetch users from the API
            try {
                let response = await axios.get('https://gorest.co.in/public/v2/users')
                // console.log(response.data)
                setUsers(response.data)
            } catch (error) {
                console.log(error)
            }
        }
        getUsers() // Call the function to fetch users
    }, [])

    const onDelete = async (userId) => { // Function to delete a user
        // Confirm deletion
        if (!window.confirm("Are you sure you want to delete this user?")) return;

        // If confirmed, make a DELETE request to the API
        try {
            await axios.delete(`https://gorest.co.in/public/v2/users/${userId}`,
                {
                    headers : {Authorization : `Bearer ${localStorage.getItem("token")}`}
                }
            )
            let temp = [...users] // Create a temporary copy of the users array

            // Filter out the deleted user from the temporary array
            temp = temp.filter(u => u.id !== userId)
            setUsers(temp)
            setMsg(`User "${users.find(u => u.id === userId).name}" (ID: ${userId}) deleted successfully!`);

        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-lg-10 mt-4 mb-4">
                    <h2>Users List</h2>
                    {
                        msg !== "" ? <div className="row">
                            <div className="col-lg-6">
                                {msg}
                            </div>
                        </div> : ""
                    }
                </div>
                <div className="col-lg-2 mt-4 mb-4">
                    <Link to="/add-user" className="btn btn-success">Add User</Link>
                    {/* <Link to='/get-user' className="btn btn-info">Get User</Link> */}
                </div>
                <div className="col-lg-12">
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">S.No.</th>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody className="table-group-divider">
                            {
                                users.map((user, index) => (
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{user.id}</td>
                                        <td>{user.name}</td>
                                        <td>{user.email}</td>
                                        <td>{user.gender}</td>
                                        <td>{user.status}</td>
                                        <td>
                                            <Link className="btn btn-primary" to={`/edit-user/${user.id}`}>Edit</Link> &nbsp;
                                            <button className="btn btn-danger" onClick={() => { onDelete(user.id) }}>Delete</button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default UserList