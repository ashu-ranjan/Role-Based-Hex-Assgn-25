import { BrowserRouter, Route, Routes } from "react-router-dom";
import UserList from "./components/UserList";
import AddUser from "./components/AddUser";
import EditUser from "./components/EditUser";
import GetUserById from "./components/GetUser";

function App(){
  return(
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<UserList/>}></Route>
          <Route path='/add-user' element={<AddUser/>}></Route>
          <Route path='/edit-user/:id' element={<EditUser/>}></Route>
          {/* <Route path='/get-user' element={<GetUserById/>}></Route> */}
        </Routes>
      </BrowserRouter>
  )
}
export default App;