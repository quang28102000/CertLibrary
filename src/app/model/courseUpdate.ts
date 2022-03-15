export interface courseUpdate{
    newId:{
      course_id: number,
      employee_id: number,
    },
    oldId: {
      course_id: number,
      employee_id: number
    },
      cert_link: String,
      status: number,
      //platform: String;
      //category: String;
      start_date: String,
      end_date: String,
      //courseLength: Number;
      is_deleted: 0
  
  }
  