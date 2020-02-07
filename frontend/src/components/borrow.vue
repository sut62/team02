<template>
 <v-app id="page">
  <v-container>
    <!-- <v-app-bar app color="blue darken-2" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>ระบบห้องสมุด</v-toolbar-title>
    </v-app-bar>-->
    <v-container>
      <div>
        <v-responsive>
          <v-flex>
            <v-card class="mx-auto" max-width="80%">
              <v-toolbar color="pink " dark flat>
                <v-toolbar-title>ระบบยืมหนังสือ</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="เลือกชื่อสมาชิก"
                        outlined
                        v-model="borrow.memberId"
                        :items="Member"
                        item-text="name"
                        item-value="memberid"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="เลือกประเภทหนังสือ"
                        outlined
                        v-model="borrow.booktypeId"
                        :items="BookTypes"
                        item-text="bookTypeName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="เลือกชื่อหนังสือ"
                        outlined
                        v-model="borrow.booknameId"
                        :items="Documents"
                        item-text="bookName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-col cols="12">
                    <v-text-field
                      label="จำนวนเล่ม"
                      name="name"
                      type="text"
                      v-model="borrow.numbers"
                      :rules="[(v) => !!v || 'Please fill in the information']"
                      required
                    ></v-text-field>
                  </v-col>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="เลือกประเภทสมาชิก"
                        outlined
                        v-model="borrow.memtpeId"
                        :items="Memtype"
                        item-text="memtype"
                        item-value="memtypeid"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-form>

                <v-col cols="12">
                    <v-text-field
                      label="เบอร์โทร"
                      name="name"
                      type="text"
                      v-model="borrow.tell"
                      :rules="[(v) => !!v || 'Please fill in the information']"
                      required
                    ></v-text-field>
                  </v-col>

                <v-snackbar v-model="snackbar">
                  {{ text }}
                  <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
                </v-snackbar>
              </v-card-text>

            

               <v-card-actions>                 
                 <v-btn color="blue-grey darken-3" dark flat to="/home"><v-icon dark left>mdi-arrow-left</v-icon>กลับสู่หน้าหลัก</v-btn>   
                 <v-btn color="teal darken-4" dark flat to="/showHistory"><v-icon>mdi-history</v-icon>แสดงประวัติการยืม</v-btn>                       
                        <v-spacer></v-spacer>                
                  <v-btn color="red darken-4" dark flat @click="saveBorrow">บันทึกข้อมูล<v-icon dark right>mdi-checkbox-marked-circle</v-icon></v-btn>                       
              </v-card-actions>

            </v-card>
          </v-flex>
        </v-responsive>
      </div>
    </v-container>
  </v-container>

 </v-app>
</template>

<script>
import http from "../http-common";
// eslint-disable-next-line no-unused-vars
export default {
  name: "borrow",
  data() {
    return {
      borrow: {
        memberId: "",
        booktypeId: "",
        booknameId: "",
        numbers: "",
        memtpeId: "",
        tell: ""
      },
      snackbar: false,
      text: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล Employee ใส่ combobox
    getCreatMember() {
      http
        .get("/member")
        .then(response => {
          this.$forceUpdate();
          this.Member = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCreatBookType() {
      http
        .get("/booktype")
        .then(response => {
          this.$forceUpdate();
          this.BookTypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Video ใส่ combobox
    getCreatDocument() {
      http
        .get("/document")
        .then(response => {
          this.$forceUpdate();
          this.Documents = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RentalType ใส่ combobox
    getCreatMemType() {
      http
        .get("/memtype")
        .then(response => {
          this.$forceUpdate();
          this.Memtype = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveBorrow() {
      console.log(this.borrow);
      http
        .post(
          //borrow/{member_id}/{bookType_id}/{document_id}/{numbers}/{memType_id}/{tell}"
          "/borrow/" +
            // parseInt(this.borrow.numbers, 10) +
            // "/" +
            this.borrow.memberId +
            "/" +
            this.borrow.booktypeId +
            "/" +
            this.borrow.booknameId +
            "/" +
            this.borrow.numbers +
            "/" +
            this.borrow.memtpeId +
            "/" +
            this.borrow.tell
            
          
        )
        .then(response => {
          console.log(response);
          this.text = "เพิ่มข้อมูลสำเร็จ"
          this.snackbar = !this.snackbar
        })
        .catch(e => {
          console.log(e);
          this.text = "ไม่สามารถเพิ่มข้อมูลได้"
          this.snackbar = !this.snackbar
        })
      this.submitted = true;
    },
    // clear() {
    //   this.borrow.memberId = "";
    //   this.borrow.booktypeId = "";
    //   this.borrow.booknameId = "";
    //   this.borrow.numbers = "";
    //   this.borrow.memtpeId = "";
    // },
    refreshList() {
      this.getCreatMember();
      this.getCreatBookType();
      this.getCreatDocument();
      this.getCreatMemType();
    }
    /* eslint-enable no-console */
  },
  created() {
    this.getCreatMember();
    this.getCreatBookType();
    this.getCreatDocument();
    this.getCreatMemType();
  }
};
</script>

<style  scoped,lang="scss" scoped>
   
#page {
  background-image: url("https://scontent.fbkk8-3.fna.fbcdn.net/v/t1.15752-9/s2048x2048/85111636_650712959008558_5153470712316428288_n.jpg?_nc_cat=111&_nc_oc=AQlqwJ-Lba_sIjxxThN8dbQg_IbX0_r1IUx6W9jd6dEAO_uFDLXpU49Ks46rRMHcYDQ&_nc_ht=scontent.fbkk8-3.fna&oh=ed77fb7f68f9a30a7276eebe68d7ea80&oe=5EDA3D53");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}


</style>

