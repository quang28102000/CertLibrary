export interface CourseRegisterDTO {
    employee_id: string;
    employee_name: string;
    course_id: string;
    course_name: string;
    platform: string;
    status: string;
    startDate: String;
    endDate: string;
    certLink: string;
    totalTime: string;
}

export interface CourseRegisterDTO2 {
    course:{
        course_tittle: string,
        platform: string,
        category: string,
        // totalLength: string
    },
    employee:{
        full_name: string,
        email: string
    },
    courseEmployee:{
        courseId: number,
        employeeId: number,
        status: string,
        startDate: string,
        endDate: string,
        certLink: string,
        isDeleted: number
    }
}