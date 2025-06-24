import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function AddUser() {

    const [msg, setMsg] = useState("")
    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [gender, setGender] = useState("")
    const [status, setStatus] = useState("")
    const navigate = useNavigate()

    const postUser = async () => {
        // Validate input fields
        if (!name || !email || !gender || !status) {
            setMsg("All fields are required");
            return;
        }
        // Validate email format
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {        
            setMsg("Please enter a valid email address");
            return;
        }
        
        try {
            let response = await axios.post('https://gorest.co.in/public/v2/users', {
                name,
                email,
                gender,
                status
            },
                {
                    headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
                }
            )
            console.log(response.data)
            setMsg("User added succesfully !!")
            navigate('/')
        } catch (error) {
            console.log(error)
            setMsg("Error: User Not Added")
        }
    }
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-6 mt-5">
                    <div className="card mt-5">
                        <div className="card-header">
                            <h2>Add User Form</h2>
                            {
                                msg !== "" ? <div className="row">
                                    <div className="col-lg-6">
                                        {msg}
                                    </div>
                                </div> : ""
                            }
                        </div>
                        <div className="card-body">
                            <div className="mb-2">
                                <label>Name:</label>
                                <input type="text" placeholder="Enter Name" onChange={($e) => setName($e.target.value)} className="form-control" />
                            </div>
                            <div className="mb-2">
                                <label>Email:</label>
                                <input type="text" placeholder="Enter Email" onChange={($e) => setEmail($e.target.value)} className="form-control" />
                            </div>
                            <div className="mb-2">
                                <label className="me-2">Gender:</label>
                                <label className="me-2">
                                    <input
                                        type="radio"
                                        value="male"
                                        checked={gender === "male"}
                                        onChange={($e) => setGender($e.target.value)}
                                    />
                                    Male
                                </label>
                                <label>
                                    <input
                                        type="radio"
                                        value="female"
                                        checked={gender === "female"}
                                        onChange={($e) => setGender($e.target.value)}
                                    />
                                    Female
                                </label>
                            </div>

                            <div className="mb-2">
                                <label className="me-2">Status  :</label>
                                <label className="me-2">
                                    <input
                                        type="radio"
                                        value="active"
                                        checked={status === "active"}
                                        onChange={(e) => setStatus(e.target.value)}
                                    />
                                    Active
                                </label>
                                <label>
                                    <input
                                        type="radio"
                                        value="inactive"
                                        checked={status === "inactive"}
                                        onChange={(e) => setStatus(e.target.value)}
                                    />
                                    Inactive
                                </label>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={() => postUser()}>Submit</button>
                        </div>
                    </div>
                </div>
                <div className="col-md-3"></div>
            </div>

        </div>
    )
}

export default AddUser