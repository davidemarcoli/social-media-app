import * as moment from "moment";

export class DateUtil {
  public static getRelativeDate(date: Date): string {
    return moment(date).fromNow();
  }

  public static getFormattedDate(date: Date): string {
    return moment(date).format("MMM Do YYYY");
  }
}
