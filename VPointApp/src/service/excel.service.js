import {jsonRequest} from '@/common/http-common'
import authHeader from "@/service/auth-header";

class ExcelService {
    addMark(mark) {
        return jsonRequest.post('mark/add', mark, {headers: authHeader()})
    }
}

export default new ExcelService();